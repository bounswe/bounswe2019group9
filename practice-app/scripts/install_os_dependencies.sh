#/usr/bin/bash
sudo yum groupinstall "Development Tools"
sudo yum install -y gcc openssl-devel bzip2-devel wget
sudo yum install -y git python3
sudo yum install -y python3-devel
sudo pip3 install uwsgi