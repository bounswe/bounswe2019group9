#!/usr/bin/env bash
cd /home/ec2-user/bounsweAss7API
echo "Installing Dependencies for Practice App"
(mkdir -p venv &&
virtualenv -p /usr/bin/python3 venv/python3 &&
source ./venv/python3/bin/activate &&
pip3 install -r prod_requirements.txt &&
echo "Successfully Installed Dependencies") ||
(>&2 echo "Failed to install Dependencies for Practice App")