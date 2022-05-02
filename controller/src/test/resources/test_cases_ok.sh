#!/usr/bin/env bash

echo "get all info of the stock"
curl -w "\n\n" -X GET "http://localhost:8080/stock?name=sport" -H "version: 2"

echo "get state of the stock"
curl -w "\n\n" -X GET "http://localhost:8080/stock/state?name=sport" -H "version: 2"

echo "update the stock with all shoe models"
curl -w "\n\n" -X PATCH "http://localhost:8080/stock?name=sport" -H "version: 2" -H "Content-Type: application/json" \
  -d '[{"color": "BLACK","size": 40,"quantity": 5},{"color": "BLACK","size": 41,"quantity": 4},{"color": "BLUE","size": 39,"quantity": 3},{"color": "BLUE","size": 39,"quantity": 6}]'

echo "update the stock multiple times with one shoe model"
curl -w "\n" -X PATCH "http://localhost:8080/stock?name=sport" -H "version: 2" -H "Content-Type: application/json" -d '[{"color": "BLACK","size": 41,"quantity": 5}]'
curl -w "\n" -X PATCH "http://localhost:8080/stock?name=sport" -H "version: 2" -H "Content-Type: application/json" -d '[{"color": "BLACK","size": 41,"quantity": 1}]'
curl -w "\n" -X PATCH "http://localhost:8080/stock?name=sport" -H "version: 2" -H "Content-Type: application/json" -d '[{"color": "BLACK","size": 41,"quantity": 10}]'
