#!/usr/bin/env bash
cd /home/ec2-user/bounsweAss7API/practice-app
mkdir -p venv
virtualenv -p /usr/bin/python3 venv/python3
source ./venv/python3/bin/activate
pip3 install -r requirements.txt
export DJANGO_SETTINGS_MODULE=practice_app.prod_settings