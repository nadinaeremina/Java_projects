package org.top.currencyconverterwebapp.converter;

// ExchangeRate - курс валюты по отношению к некоторой валюте в виде рекорда
// code - код системы исчисления
// rate - коэффициент курса
public record ExchangeRate(String code, double rate) {
}
