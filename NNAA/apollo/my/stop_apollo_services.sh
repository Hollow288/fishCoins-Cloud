#!/bin/bash

# 停止 Apollo Portal
echo "Stopping Apollo Portal..."
cd "E:/Program Files/apollo-portal-2.3.0-github/scripts"
./shutdown.sh
if [ $? -ne 0 ]; then
    echo "Failed to stop Apollo Portal. Exiting..."
    exit 1
fi
echo "Apollo Portal stopped successfully!"

# 等待 Portal 服务停止
echo "Waiting for Apollo Portal to be fully stopped..."
sleep 10  # 等待 10 秒，确保门户服务完全停止

# 停止 Apollo Admin Service
echo "Stopping Apollo Admin Service..."
cd "E:/Program Files/apollo-adminservice-2.3.0-github/scripts"
./shutdown.sh
if [ $? -ne 0 ]; then
    echo "Failed to stop Apollo Admin Service. Exiting..."
    exit 1
fi
echo "Apollo Admin Service stopped successfully!"

# 等待 Admin Service 停止完成
echo "Waiting for Apollo Admin Service to be fully stopped..."
sleep 10  # 等待 10 秒，确保认证服务完全停止

# 停止 Apollo Config Service
echo "Stopping Apollo Config Service..."
cd "E:/Program Files/apollo-configservice-2.3.0-github/scripts"
./shutdown.sh
if [ $? -ne 0 ]; then
    echo "Failed to stop Apollo Config Service. Exiting..."
    exit 1
fi
echo "Apollo Config Service stopped successfully!"
