#!/bin/bash
apt-get update -y
apt-get install -y openjdk-11-jdk firefox chromium-browser wget bash unzip awscli
rm -rf /var/lib/apt/lists/*

# Installs geckodriver to /usr/bin/geckodriver
# Installs chromedriver to /usr/bin/chromedriver
# Based on freebsd liscence snippet from https://askubuntu.com/questions/870530/how-to-install-geckodriver-in-ubuntu

## Geckodriver
wget https://github.com/mozilla/geckodriver/releases/download/v0.24.0/geckodriver-v0.24.0-linux64.tar.gz
sh -c 'tar -x geckodriver -zf geckodriver-v0.24.0-linux64.tar.gz -O > /usr/bin/geckodriver'
chmod +x /usr/bin/geckodriver
rm geckodriver-v0.24.0-linux64.tar.gz

## Chromedriver
wget https://chromedriver.storage.googleapis.com/80.0.3987.106/chromedriver_linux64.zip
unzip chromedriver_linux64.zip
chmod +x chromedriver
mv chromedriver /usr/bin/
rm chromedriver_linux64.zip
