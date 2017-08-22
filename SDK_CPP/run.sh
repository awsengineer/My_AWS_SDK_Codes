distro="Ubuntu"
cat /proc/version  |grep "Red Hat" && distro="RHEL"
if [ $distro = "RHEL"]
then
  yum install -y wget docker
else
  apt-get update && apt-get -y install wget docker
fi

mkdir /tmp/awssdkcpp
wget https://raw.githubusercontent.com/awsengineer/My_AWS_SDK_Codes/master/SDK_CPP/Dockerfile -O /tmp/awssdkcpp/Dockerfile
docker build -t awsengineer/aws-sdk-cpp-playground:1.0 /tmp/awssdkcpp

docker images

