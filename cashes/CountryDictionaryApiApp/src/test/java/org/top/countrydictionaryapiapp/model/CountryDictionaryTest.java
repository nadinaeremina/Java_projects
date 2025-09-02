package org.top.countrydictionaryapiapp.model;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.top.countrydictionaryapiapp.model.exception.CountryNotFound;
import org.top.countrydictionaryapiapp.model.exception.InvalidCode;
import org.top.countrydictionaryapiapp.model.exception.InvalidCountry;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CountryDictionaryTest {

    private CountryDictionary dictionary;
    private CountryStorage storage;

    @BeforeEach
    public void setUp() {
        storage = Mockito.mock(CountryStorage.class, invocation -> {
            throw new UnsupportedOperationException(invocation.getMethod().getName());
        });
        dictionary = new CountryDictionary(storage);
    }

    @Nested
    public class ListAllTest {

        @Test
        public void success() {
            List<Country> expected = List.of(new Country());
            Mockito.doReturn(expected)
                    .when(storage).selectAll();
            List<Country> actual = dictionary.listAll();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class getByCodeTest {

        @Test
        public void success() {
            String originCode = "ru";
            Country expected = new Country(originCode, "Rus", "Russia");
            Mockito.doReturn(Optional.of(expected))
                    .when(storage).selectByCode(originCode);
            Country actual = dictionary.getByCode(originCode);
            assertEquals(expected, actual);
        }

        @Test
        public void invalidCode(){
            String originCode = "Ru";
            assertThrows(InvalidCode.class, () ->{
                dictionary.getByCode(originCode);
            });
        }

        @Test
        public void countryNotFound(){
            String originCode = "ru";
            Mockito.doReturn(Optional.empty())
                    .when(storage).selectByCode(originCode);
            assertThrows(CountryNotFound.class, () ->{
                dictionary.getByCode(originCode);
            });
        }
    }

    @Nested
    public class importSingleTest {

        @Test
        public void importNew() {
            Country originCountry = new Country("ru", "Rus", "Russia");
            Mockito.doReturn(Optional.empty())
                .when(storage).selectByCode(originCountry.getAlpha2Code());
            Mockito.doNothing().when(storage).insert(originCountry);
            dictionary.importSingle(originCountry);
            Mockito.verify(storage).insert(originCountry);
        }

        @Test
        public void importExisting() {
            Country originCountry = new Country("ru", "Rus", "Russia");
            Mockito.doReturn(Optional.of(originCountry))
                    .when(storage).selectByCode(originCountry.getAlpha2Code());
            Mockito.doNothing().when(storage).update(originCountry);
            dictionary.importSingle(originCountry);
            Mockito.verify(storage).update(originCountry);
        }

        @Test
        public void importInvalid() {
            Country originCountry = new Country();
            assertThrows(InvalidCountry.class, () -> {
                dictionary.importSingle(originCountry);
            });
        }
    }

    @Nested
    public class importBatchTest {
        
        @Test
        public void success() {

            Country expectedImported = new Country("ru", "Russia", "Russian Federation");
            Country existing =  new Country("ex", "Existing", "Existing country");
            List<Country> originCountries = List.of(
                new Country(),
                expectedImported,
                new Country("af", "afg", "Afganistan"),
                new Country("af", "AFG", "Афганистан"),
                existing
            );
            Mockito.doReturn(List.of(existing)).when(storage).selectAll();
            Mockito.doNothing().when(storage).insertAll(Mockito.anyList());
            dictionary.importBatch(originCountries);
            Mockito.verify(storage).insertAll(Mockito.argThat(countries -> {
                assertEquals(1, countries.size());
                assertEquals(expectedImported, countries.getFirst());
                return true;
            }));
        }
    }
}
