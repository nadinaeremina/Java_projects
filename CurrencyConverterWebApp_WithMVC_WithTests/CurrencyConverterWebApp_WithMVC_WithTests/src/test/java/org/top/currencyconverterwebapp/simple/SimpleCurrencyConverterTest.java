package org.top.currencyconverterwebapp.simple;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.top.currencyconverterwebapp.converter.ExchangeRate;
import org.top.currencyconverterwebapp.converter.InvalidValueException;
import org.top.currencyconverterwebapp.converter.RatesProvider;
import org.top.currencyconverterwebapp.converter.UnsupportedCurrencyException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCurrencyConverterTest {

    private final double delta = 0.00001;

    private RatesProvider ratesProviderMock;
    private SimpleCurrencyConverter converter;

    @BeforeEach
    void setUp() {
        ratesProviderMock = Mockito.mock(RatesProvider.class, invocation -> {
            String msg = invocation.getMethod().getName() + ", args: " + Arrays.toString(invocation.getArguments());
            throw new UnsupportedOperationException(msg);
        });
        converter = new SimpleCurrencyConverter(ratesProviderMock);
    }

    @Test
    public void success() {
        ExchangeRate originExchangeRate = new ExchangeRate("USD", 78.1);
        ExchangeRate baseExchangeRate = new ExchangeRate("RUB", 1);
        // настройка моки для нужного вызова
        Mockito.doReturn(
                        List.of(originExchangeRate, baseExchangeRate) // что вернется при вызове getRates
                ).when(ratesProviderMock) // для объекта ratesProviderMock
                .getRates("RUB"); // говорим что будет вызываться getRates с параметром originBaseCurrency

        // тестирование
        double originValue = 100;
        double expectedValue = 7810;
        double actualValue = converter.convert("USD", "RUB", originValue);

        // проверка
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void invalidValueException() {
        // настройка моки для нужного вызова
        Mockito.doReturn(List.of()).when(ratesProviderMock).getRates(Mockito.any());
        // тут хотим чтобы вылетел InvalidValueException
        assertThrows(InvalidValueException.class, () -> {
            converter.convert("", "", -1);
        });
    }

    @Test
    public void unsupportedCurrencyException() {
        String originKnown = "KNOWN";
        Mockito.doReturn(List.of(new ExchangeRate(originKnown, 1)))
                .when(ratesProviderMock)
                .getRates(Mockito.any());
        // тут хотим чтобы вылетел UnsupportedCurrencyException для обоих случаев
        assertThrows(UnsupportedCurrencyException.class, () -> {
            converter.convert("", originKnown, 1);
        });
        assertThrows(UnsupportedCurrencyException.class, () -> {
            converter.convert(originKnown, "", 1);
        });
    }
}
