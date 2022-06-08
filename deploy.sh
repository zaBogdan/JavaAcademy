#!/bin/bash

DOCKER_HOST="ssh://root@138.68.138.6" docker-compose up -d --build
unset DOCKER_HOST