# Versions descriptions

## 1.0.0
### API endpoints
#### units
+ GET /api/v1/units - units list
+ POST /api/v1/units - create new  unit
+ GET /api/v1/units/{number} - get single unit
+ DELETE /api/v1/units/{number} - delete unit
+ PATCH /api/v1/units/{number} - update unit

#### BATCH endpoints
+ POST /batch/v1/units - populate db with units


## 1.1.0

#### rates
+ GET /api/v1/rates/{assembly} - get specification
+ GET /api/v1/rates/{assembly}?plain=true - get plain specification
+ POST /api/v1/rates/{assembly} - create new rate
+ DELETE /api/v1/rates/{assembly}/{unit} - delete rate
+ PATCH /api/v1/rates/{assembly}/{unit} - update rate

#### BATCH endpoints
+ POST /batch/v1/rates - populate db with rates