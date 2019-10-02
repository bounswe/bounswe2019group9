#!/usr/bin/env bash
sudo yum groupinstall -y "Development Tools"
sudo yum install -y gcc openssl-devel bzip2-devel wget
sudo amazon-linux-extras install -y nginx1.12
sudo yum install -y postgresql-server postgresql-contrib
sudo postgresql-setup initdb
sudo systemctl start postgresql
sudo systemctl enable postgresql
sudo systemctl start nginx
sudo systemctl enable nginx

# this file is missing most of the configuration required to start a new server, the current server is mostly hand-configured instead, sadly
