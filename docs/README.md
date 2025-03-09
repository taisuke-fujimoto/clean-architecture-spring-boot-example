# clean-architecture-spring-boot-example

クリーンアーキテクチャ + Spring Boot の実装例です  
クリーンアーキテクチャの依存ルールを守らせるためのモジュール構造としています

## Usage

1. 開発用インフラの起動
   ```shell
   ./gradlew dev-infra:composeUp
   ```
   - 5432 ポートを DB が使用

2. task-manager-app の実行
   ```shell
   ./gradlew apps:task-manager-app:bootRun
   ```
   - ランダムポートのため、起動時のログを確認
   - タスクの登録
     ```
     POST http://localhost:{ポート}/tasks
     { "title": "task1" }
     ```
   - 登録したタスクの確認
     ```
     GET http://localhost:{ポート}/tasks/{タスクID}
     ```
   - タスクの完了
     ```
     PUT http://localhost:{ポート}/tasks/{タスクID}/complete
     ```

3. task-viewer-app の実行
   ```shell
   ./gradlew apps:task-viewer-app:bootRun
   ```
   - ランダムポートのため、起動時のログを確認
   - タスク一覧を表示
     - すべてのタスク `GET http://localhost:{ポート}/tasks`
     - 完了済タスク `GET http://localhost:{ポート}/tasks?completed=true`
     - 未完了タスク `GET http://localhost:{ポート}/tasks?completed=false`
