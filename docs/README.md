# clean-architecture-spring-boot-example

クリーンアーキテクチャ + Spring Boot の実装例です  
モジュールの依存方向とクリーンアーキテクチャのレイヤーの依存方向を一致させて、クリーンアーキテクチャの依存ルールを守ります

- [クリーンアーキテクチャ採用時のモジュール分割のすゝめ (Gradle, Kotlin)](https://note.com/pa_tf/n/n0b10d4b98782)

## Module Structure

```mermaid
flowchart BT
  subgraph entities
    task-entity
    task-entity-reader
    task-entity-writer
  end
  subgraph use-cases
    task-manage-use-case
    task-view-use-case
    task-view-use-case-query
  end
  subgraph gateways
    postgresql-gateway
  end
  subgraph apps
    task-manager-app
    task-viewer-app
  end
  task-entity-reader --> task-entity
  task-entity-writer --> task-entity
  task-manage-use-case --> task-entity & task-entity-reader & task-entity-writer
  task-view-use-case-query --> task-entity
  task-view-use-case --> task-entity & task-view-use-case-query
  postgresql-gateway ---> task-entity & task-entity-reader & task-entity-writer & task-view-use-case-query
  task-manager-app --> task-entity & task-manage-use-case & postgresql-gateway
  task-viewer-app --> task-entity & task-view-use-case & postgresql-gateway
```
- A → B は依存の方向 (A が B に依存する)

## Module Overview

<table>
  <tbody>
    <tr>
      <th>task-entity</th>
      <td>TaskEntity を提供</td>
    </tr>
    <tr>
      <th>task-entity-reader</th>
      <td>TaskEntity の取得 interface を提供 (実装は gateway モジュール)</td>
    </tr>
    <tr>
      <th>task-entity-writer</th>
      <td>TaskEntity の保存 interface を提供 (実装は gateway モジュール)</td>
    </tr>
    <tr>
      <th>task-manage-use-case</th>
      <td>タスク管理の use-case interface を提供 (実装も同居)</td>
    </tr>
    <tr>
      <th>task-view-use-case</th>
      <td>タスク一覧参照の use-case interface を提供 (実装も同居)</td>
    </tr>
    <tr>
      <th>task-view-use-case-query</th>
      <td>task-view-use-case 用のクエリ interface を提供 (実装は gateway モジュール)</td>
    </tr>
    <tr>
      <th>postgresql-gateway</th>
      <td>
        Entity の保存・取得 interface と use-case 用クエリ interface を実装<br>
        このモジュールを実行するためには spring boot r2dbc のシステムプロパティ設定が必要 (通常、アプリモジュールの application.yml 等で記述)<br>
      </td>
    </tr>
    <tr>
      <th>task-manager-app</th>
      <td>タスク管理アプリ</td>
    </tr>
    <tr>
      <th>task-viewer-app</th>
      <td>タスク一覧参照アプリ</td>
    </tr>
  </tbody>
</table>

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
