#!/usr/bin/env bash
sudo yum groupinstall "Development Tools"
sudo yum install -y gcc openssl-devel bzip2-devel wget
sudo yum install -y nginx
sudo yum -y install mariadb-server
sudo service mariadb start
sudo service nginx start
sudo yum install -y git python3
sudo yum install -y python3-devel
sudo pip3 install uwsgi

# this file is missing most of the configuration required to start a new server, the current server is mostly hand-configured instead, sadly