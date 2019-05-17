#!/bin/bash -eux

## INSTALL docker

# add certificates Docker
apt-get install -y \
    apt-transport-https \
    ca-certificates \
    curl \
    software-properties-common

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

fingerprint_check=`apt-key fingerprint 0EBFCD88 | grep 'Key fingerprint = 9DC8 5822 9FC7 DD38 854A  E2D8 8D81 803C 0EBF CD88'`

if [[ $fingerprint_check ]]
then
    echo "Fingerprint check passed, continuing to install Docker"
else
    echo "Fingerprint check did not pass!!!"
    exit 1;
fi

add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable edge"

apt-get -y update
apt-get purge lxc-docker

apt-get install -y docker-ce="18.06.1~ce~3-0~ubuntu"

service docker stop
service docker start

# handle permissions
sudo usermod -aG docker vagrant

## INSTALL docker-compose  

curl -fL https://github.com/docker/compose/releases/download/1.21.1/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
