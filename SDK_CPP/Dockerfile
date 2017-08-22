FROM ubuntu
RUN apt-get update && apt-get install -y curl
RUN apt-get update &&  apt-get -y install vim libcurl4-openssl-dev libssl-dev uuid-dev zlib1g-dev libpulse-dev ncurses* git wget awscli sudo mlocate curl
RUN mkdir /cppsdk && cd /cppsdk && git clone https://github.com/aws/aws-sdk-cpp.git && ls  /cppsdk/
RUN cd /tmp && wget https://cmake.org/files/v3.9/cmake-3.9.0.tar.gz && tar xvzf cmake-3.9.0.tar.gz
RUN cd /tmp/cmake-3.9.0 && ./configure && make -j36 &&  make install 
RUN mkdir -p /cppsdk/sdk_build && cd /cppsdk/sdk_build && cmake /cppsdk/aws-sdk-cpp/ && make -j36 &&  make install
RUN mkdir -p /home/ubuntu
RUN cd /home/ubuntu &&  git clone https://github.com/awsdocs/aws-doc-sdk-examples.git
RUN mkdir -p /home/ubuntu/aws-doc-sdk-examples/cpp/s3_build 
RUN cd /home/ubuntu/aws-doc-sdk-examples/cpp/s3_build && cmake -Daws-sdk-cpp_DIR=/cppsdk/sdk_build ../example_code/s3
RUN cp /usr/local/lib/libaws-cpp-sdk-* /cppsdk/sdk_build/aws-cpp-sdk-s3/
RUN cp /usr/local/lib/libaws-cpp-sdk-core.so  /cppsdk/sdk_build/aws-cpp-sdk-core
RUN sed -i "23i (void)argc; (void)argv;" /home/ubuntu/aws-doc-sdk-examples/cpp/example_code/s3/list_buckets.cpp
RUN sed -i "42i (void)access;" /home/ubuntu/aws-doc-sdk-examples/cpp/example_code/s3/set_acl.cpp
RUN sed -i "70i (void)access;" /home/ubuntu/aws-doc-sdk-examples/cpp/example_code/s3/set_acl.cpp
RUN cd /home/ubuntu/aws-doc-sdk-examples/cpp/s3_build &&  make -j 36
