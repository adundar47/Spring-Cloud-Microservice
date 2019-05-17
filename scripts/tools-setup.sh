#!/bin/bash -eux

apt-get update -y

apt-get install -y zip unzip curl wget vim tree jq make htop python3-pip

apt-get install -y screen bzr apache2-utils

swapoff --all

sed -e '/swap/ s/^#*/#/' -i /etc/fstab

LC_ALL=C pip3 install --upgrade pip
