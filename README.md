# SpectroCoin interview task
# 
This application allows for user to exchange a currency to another currency from given list.

* Start application by running CurrencyExchangeApplication class.

* To get all available currencies exchange rates call GET "/api/v1/exchange-rate".

* To exchange currency to another currency call GET "/api/v1/exchange" with request body.
* Body example of GET "/api/v1/exchange": `{
  "initialCurrency": "GBP",
  "finalCurrency": "BTC",
  "quantity": 10
  }`