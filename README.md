# flyway-test

SpringBoot + MySQL + Docker　環境でGradleからflywayによるマイグレーションを  
手動実行するサンプルプロジェクトです。    

## 環境

+ macOS Sierra 10.12.6  
+ Docker for Mac Version 17.09.0-ce-mac35 (19611)  

## 起動  
このリポジトリをチェックアウトしたディレクトリ以下で  
`docker-compose up`  
※初回起動ではgradleのインストールが走るためかなり時間がかかります  
api_1 | ~(中略)~ : Started Application in 13.72 seconds (JVM running for 14.701)  

## マイグレーションテスト準備  
Gradleからflywayを実行する際にはflywayから始まる下記タスクを実行する必要があります  

| タスク名 | 内容 |
|:---|----|
| flywayInfo | 現在のマイグレーション状況を表示 |
| flywayInit | flyway用のメタテーブルを作成 |
| flywayMigrate | マイグレーションを実行 |

初回起動が完了したら別ターミナルから`docker exec -it flywaytest_api_1`で  
test-apiの起動しているコンテナに入ります  

初期状態ではflywayの管理下には無いためそれを確認します。  
コンテナ内にはgradleのパスが通っていないため代わりに実行ディレクトリにあるgradlewを使用します。  
`./gradlew flywayInfo`  

これで確認が出来たので、Initを実行します。  
`./gradlew flywayInit`  

改めて確認すると下記のような出力が得られるはずです。  

| Version        | Description                | Installed on        | State   |
|:---------------|:---------------------------|:--------------------|:--------|
| 1              | << Flyway Init >>          | 2017-10-09 13:09:10 | Success |
| 2              | modify testuser            |                     | Pending |
| 3              | add post table             |                     | Pending |

## マイグレーションテスト  
上記が完了した時点で `./gradlew flywayMigrate` をかけるとバージョンが1から3まで全てのマイグレーションが  
実行されますが、実際にはスキーマだけ先に作成して適用は別のタイミングで実施したいなどのケースがままあります。  

GradleからflywayMigrateを実行する際に指定バージョンまでのマイグレーションを行う方法には２つあります。  

+ gradle flywayMigrateにパラメータを指定する方法  

`./gradlew flywayMigrate -Pflyway.target=2`のような形でtargetを指定することができます。  
指定したいのが上げたいバージョンまでであればこの方法で問題ありません。

+ build.gradleにtargetを指定する方法  

build.gradleのflywayのブロックにtargetを指定する方法でも実施が可能です。  
他の設定項目を細かに行え、一度ファイルに記載するためオペミスが発生しにくいという利点があります。  
`./gradlew flywayMigrate`を行うとbuild.gradleで指定したバージョンまでしかマイグレーションが実施されません。  

### 参考
https://flywaydb.org/documentation/gradle/



