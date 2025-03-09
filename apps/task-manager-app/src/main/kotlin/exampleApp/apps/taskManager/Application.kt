package exampleApp.apps.taskManager

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackageClasses = [
        Application::class,
        exampleApp.gateways.postgresql.clients.PackageId::class,
        exampleApp.gateways.postgresql.impl.entities.task.PackageId::class,
        exampleApp.useCases.taskManage.PackageId::class,
    ],
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
