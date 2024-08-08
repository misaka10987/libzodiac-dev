#!/bin/bash
# git submodule foreach "git add ."
git submodule foreach "git commit -a -m \"$1\""
# git add .
git commit -m "$1"
