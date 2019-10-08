 #!/bin/bash

# run this script as sudo!!!


# install updates
yum update -y

# install apache httpd
yum install httpd -y

# install java 8
yum install java-1.8.0 -y
# remove java 1.7
yum remove java-1.7.0-openjdk -y

# create the working directory
mkdir /opt/spring-boot-ec2-demo
# create configuration specifying the used profile
echo "RUN_ARGS=--spring.profiles.active=ec2" > /opt/spring-boot-ec2-demo/spring-boot-ec2-demo.conf

# download the maven artifact from S3
aws s3 cp s3://bounswe-backend-bucket/language-learning-platform-0.1.0.jar /opt/spring-boot-ec2-demo/language-learning-platform.jar --region=eu-central-1

# create a springboot user to run the app as a service
useradd springboot
# springboot login shell disabled
chsh -s /sbin/nologin springboot
chown springboot:springboot /opt/spring-boot-ec2-demo/language-learning-platform.jar
chmod 500 /opt/spring-boot-ec2-demo/language-learning-platform.jar

# create a symbolic link
ln -s /opt/spring-boot-ec2-demo/language-learning-platform.jar /etc/init.d/spring-boot-ec2-demo

# forward port 80 to 8080
echo "<VirtualHost *:80>
    ProxyRequests Off
    ProxyPass / http://localhost:8080/
    ProxyPassReverse / http://localhost:8080/
</VirtualHost>" >> /etc/httpd/conf/httpd.conf

# start the httpd and spring-boot-ec2-demo
service httpd start
service spring-boot-ec2-demo start

# automatically start httpd and spring-boot-ec2-demo if this ec2 instance reboots
chkconfig httpd on
chkconfig spring-boot-ec2-demo on


# Install CodeDeploy

sudo yum install ruby -y
cd /home/ec2-user
wget https://aws-codedeploy-eu-central-1.s3.eu-central-1.amazonaws.com/latest/install
chmod +x ./install
./install auto
chkconfig codedeploy-agent on
rm install
