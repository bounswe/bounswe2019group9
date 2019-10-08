#!/usr/bin/env bash
sudo chmod 500 /home/ec2-user/language-learning-platform.jar
sudo chown springboot:springboot /home/ec2-user/language-learning-platform.jar
sudo mv /opt/spring-boot-ec2-demo/language-learning-platform.jar /opt/spring-boot-ec2-demo/language-learning-platform.jar.bkp
sudo mv /home/ec2-user/language-learning-platform.jar /opt/spring-boot-ec2-demo/language-learning-platform.jar
