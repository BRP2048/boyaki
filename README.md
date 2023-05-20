# boyaki
ぼやけます

## About
DiscordのWebhook機能を使って特定のチャンネルに匿名でメッセージを送ることができます。
仲間内のサーバに怪文書を投げ込める空間がほしいときに最適です。

## installation
```
sbt dist
```
application.confの以下を修正
```
# https://www.playframework.com/documentation/latest/Configuration
play.http.secret.key="application secretを生成し記載"
passwordDigest="任意のパスワードのBCrypt hashを記載"
webHookUrl="discordのwebhook urlを記載"
```
任意で`play.filters.hosts`項目を追加しアクセス制限を適当に設定してください。
