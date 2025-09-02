package org.top.countrydictionaryapiapp.model;

import org.springframework.stereotype.Service;
import org.top.countrydictionaryapiapp.model.exception.CountryNotFound;
import org.top.countrydictionaryapiapp.model.exception.InvalidCode;
import org.top.countrydictionaryapiapp.model.exception.InvalidCountry;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// CountryDictionary - справочник стран
@Service
public class CountryDictionary {

    private final CountryStorage storage;

    public CountryDictionary(CountryStorage storage) {
        this.storage = storage;
    }

    // listAll - получение списка всех стран
    // вход: -
    // выход: список всех стран
    // исключения: -
    public List<Country> listAll() {
        return storage.selectAll();
    }

    // getByCode - получение страны по коду
    // вход: iso alpha 2 код страны
    // выход: страна с заданным кодом
    // исключения: CountryNotFound, InvalidCode
    public Country getByCode(String alpha2Code) {
        checkCode(alpha2Code);
        Optional<Country> country = storage.selectByCode(alpha2Code);
        if (country.isEmpty()) {
            throw new CountryNotFound();
        }
        return country.get();
    }

    // importSingle - импортирование данных о стране
    // вход: объект импортируемой страны
    // выход: -
    // исключения: InvalidCountry
    // примечание: если уже существует страна с заданным кодом, то ее поля обновляются в соответствие с импортируемыми данными
    public void importSingle(Country country) {
        checkCountry(country);
        Optional<Country> existing = storage.selectByCode(country.getAlpha2Code());
        if(existing.isPresent()) {
            storage.update(country);
        }
        else{
           storage.insert(country);
        }
    }

    // importBatch - импортирование данных о множестве стран
    // вход: список импортируемых стран
    // выход: -
    // исключения: -
    // примечание:
    //  - если для очередной страны уже существует страна с таким же кодом, то этот объект пропустить при импорте;
    //  - если очередной объект страны не является валидным, то пропустить этот объект при импорте
    //  - если среди входных стран есть страны с одинаковыми кодами, то пропустить такие страны при импорте
    public void importBatch(List<Country> countries) {
        // отфильтруем входные страны (преимущественно, дешевая проверка)

        // 1, отфильтруем валидные страны
        List<Country> valid = countries.stream().filter(c -> {
            try {
                checkCountry(c);
                return true;
            } catch (InvalidCountry e) {
                return false;
            }
        }).toList();

        // 2. соберем страны с неповторяющимся кодами
        // подготвим мапу с ключами-кодами и значениями-кол-вом повторений кодов
        Map<String, Integer> repeatsByCode = new HashMap<>();
        for (Country country : valid) {
            String code = country.getAlpha2Code();
            if (repeatsByCode.containsKey(code)) {
                repeatsByCode.put(code, repeatsByCode.get(code) + 1);
            } else {
                repeatsByCode.put(code, 1);
            }
        }
        // возьмем коды для удаления из списка на добавление стран
        List<String> codesToRemove = repeatsByCode
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
        // удалим страны с кодами для удаления
        List<Country> toInsert = valid
                .stream()
                .filter(c -> !codesToRemove.contains(c.getAlpha2Code()))
                .toList();

        if (toInsert.isEmpty()) {
            // если после проверки не осталось данных - то завершить операцию
            return;
        }

        // 3. оставим только страны, кодов которых нет в БД
        List<Country> existing = storage.selectAll();
        toInsert = toInsert
                .stream()
                .filter(c -> !existing.contains(c))
                .toList();
        if (toInsert.isEmpty()) {
            // если после проверки не осталось данных - то завершить операцию
            return;
        }

        // 4. вставить данные
        storage.insertAll(toInsert);
    }

    // checkCode - вспомогательный метод проверки кода
    private void checkCode(String code) {
        if (code == null) {
            throw new InvalidCode();
        }
        String validCode = "^[a-z]{2}$";
        if (!code.matches(validCode)) {
            throw new InvalidCode();
        }
    }

    private void checkCountry(Country country) {
        try{
            checkCode(country.getAlpha2Code());
        } catch (InvalidCode e){
            throw new InvalidCountry();
        }
        if(country.getShortName() == null || country.getShortName().isEmpty() ||
        country.getOfficialName() == null || country.getOfficialName().isEmpty()) {
            throw new InvalidCountry();
        }
    }
}
