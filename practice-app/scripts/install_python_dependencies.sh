#!/usr/bin/env bash
cd /home/ec2-user/bounsweAss7API
echo "Installing Dependencies for Practice App"
(mkdir -p venv &&
virtualenv venv -p python3 &&
source venv/bin/activate &&
pip3 install -U pip &&
PYTHON_INSTALL_LAYOUT="" pip3 install -r requirements.txt &&
PYTHON_INSTALL_LAYOUT="" pip3 install -r prod_requirements.txt &&
echo "Successfully Installed Dependencies") ||
(>&2 echo "Failed to install Dependencies for Practice App")