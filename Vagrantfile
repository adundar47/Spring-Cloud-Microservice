IP_ADDRESS="172.22.47.47"

Vagrant.configure("2") do |config|

    (1..1).each do |i|
    config.vm.define "micro" do |node|

      node.vm.box = "ubuntu/xenial64"
	  
      node.vm.hostname = "micro"
      node.vm.network "private_network", ip: "#{IP_ADDRESS}"
     
      node.vm.provision "shell", inline: "echo 'cd /vagrant/docker' >> ~/.bashrc && exit", privileged: false
      
      node.vm.provision "shell", inline: "sudo cp /vagrant/sshd_config /etc/ssh/sshd_config"
      node.vm.provision "shell", inline: "sudo service ssh restart"
      
      node.vm.provision "shell", inline: "sudo apt-get -y update"
     
      node.vm.provision "shell", inline: "sudo /bin/bash /vagrant/scripts/docker-setup.sh"
      node.vm.provision "shell", inline: "sudo /bin/bash /vagrant/scripts/tools-setup.sh"

      node.vm.provision "shell", inline: $docker_host_override_conf, privileged: true
      node.vm.provision "shell", inline: $docker_host_daemon_conf, privileged: true

      node.vm.provider "virtualbox" do |vb|
        vb.memory = "3096"
        vb.cpus = "2"
      end
    end
  end
end

  
$docker_host_override_conf = <<SCRIPT
mkdir -p /etc/systemd/system/docker.service.d

cat > /etc/systemd/system/docker.service.d/override.conf <<EOF
[Service]
ExecStart=
ExecStart=/usr/bin/dockerd
EOF
SCRIPT

$docker_host_daemon_conf = <<SCRIPT

cat > /etc/docker/daemon.json <<EOF
{
  "hosts": [
      "fd://",
      "unix:///var/run/docker.sock",
      "tcp://127.0.0.1:2375",
      "tcp://#{IP_ADDRESS}:2375"
  ] 
}
EOF

systemctl daemon-reload
systemctl restart docker
SCRIPT
