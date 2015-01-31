# ingress_key_recylcer
Automated tool to recycle portal keys for Ingress.

# Dependency
adb
usb debug

# Limitation
* Only Nexus 6 is supported for now.
  * Other devices can be supported by adding config files.
* Tail keys in currently selected order are recylced (by Title or by Distance)

# How to use
* Connect your device to PC
* Launch ingress
* Set the order, by Title or by Distance
* sbt run [num_keys_to_be_recycled]


# ingress_key_recylcer
自動でIngressのポータルキーをリサイクル

# 必要なツール
adb
usb debug

# 現在の制限
* Nexus 6 のみサポート
  * config ファイルの追加により端末を追加可能
*  by Title か by Distance順で末尾のものからリサイクルされます

# 使い方
* 端末をPCに接続します
* Ingressを起動
* by Title or by Distanceを選択
* sbt run [num_keys_to_be_recycled]
