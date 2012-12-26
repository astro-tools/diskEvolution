#!/bin/bash

cd "$( dirname "${BASH_SOURCE[0]}" )"

java -cp lib/*:build/jar/* edu.asu.sese.diskEvolution.Main
