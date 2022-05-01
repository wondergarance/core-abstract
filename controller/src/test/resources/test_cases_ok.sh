#!/usr/bin/env bash

# get all info of the stock
curl -X GET "http://localhost:8080/stock?name=sport" -H "version: 2"

# get state of the stock
curl -X GET "http://localhost:8080/stock/state?name=sport" -H "version: 2"

# update the stock with all shoe models
curl -X  PATCH "http://localhost:8080/stock?name=sport" -H "version: 2" -H "Content-Type: application/json" -d '[{"color": "BLACK","size": 40,"quantity": 5},{"color": "BLACK","size": 41,"quantity": 4},{"color": "BLUE","size": 39,"quantity": 3},{"color": "BLUE","size": 39,"quantity": 6}]'

# update the stock multiple times with one shoe model
curl -X  PATCH "http://localhost:8080/stock?name=sport" -H "version: 2" -H "Content-Type: application/json" -d '[{"color": "BLACK","size": 41,"quantity": 5}]'
curl -X  PATCH "http://localhost:8080/stock?name=sport" -H "version: 2" -H "Content-Type: application/json" -d '[{"color": "BLACK","size": 41,"quantity": 1}]'
curl -X  PATCH "http://localhost:8080/stock?name=sport" -H "version: 2" -H "Content-Type: application/json" -d '[{"color": "BLACK","size": 41,"quantity": 10}]'
