#!/bin/bash

# 启动 Apollo Config Service
echo "Starting Apollo Config Service..."
cd "E:/Program Files/apollo-configservice-2.3.0-github/scripts"
./startup.sh
if [ $? -ne 0 ]; then
    echo "Failed to start Apollo Config Service. Exiting..."
    exit 1
fi
echo "Apollo Config Service started successfully!"

# 等待 Config Service 启动完成
echo "Waiting for Apollo Config Service to be fully started..."
sleep 10  # 等待 10 秒，确保配置服务完全启动

# 启动 Apollo Admin Service
echo "Starting Apollo Admin Service..."
cd "E:/Program Files/apollo-adminservice-2.3.0-github/scripts"
./startup.sh
if [ $? -ne 0 ]; then
    echo "Failed to start Apollo Admin Service. Exiting..."
    exit 1
fi
echo "Apollo Admin Service started successfully!"

# 等待 Admin Service 启动完成
echo "Waiting for Apollo Admin Service to be fully started..."
sleep 10  # 等待 10 秒，确保认证服务完全启动

# 启动 Apollo Portal
echo "Starting Apollo Portal..."
cd "E:/Program Files/apollo-portal-2.3.0-github/scripts"
./startup.sh
if [ $? -ne 0 ]; then
    echo "Failed to start Apollo Portal. Exiting..."
    exit 1
fi
echo "Apollo Portal started successfully!"
