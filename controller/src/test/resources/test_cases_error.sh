#!/usr/bin/env bash

# the capacity of one shoe model exceeds the limite 30
curl -X  PATCH "http://localhost:8080/stock?name=sport" -H "version: 2" -H "Content-Type: application/json" -d '[{"color": "BLACK","size": 41,"quantity": 31}]'

# the capacity of the stock exceeds the limite 30
curl -X  PATCH "http://localhost:8080/stock?name=sport" -H "version: 2" -H "Content-Type: application/json" -d '[{"color": "BLACK","size": 40,"quantity": 5},{"color": "BLACK","size": 41,"quantity": 4},{"color": "BLUE","size": 39,"quantity": 3},{"color": "BLUE","size": 39,"quantity": 22}]'

# the capacity of one shoe model exceeds the limite 0
curl -X  PATCH "http://localhost:8080/stock?name=sport" -H "version: 2" -H "Content-Type: application/json" -d '[{"color": "BLACK","size": 41,"quantity": -1}]'
