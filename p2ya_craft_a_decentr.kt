// Configuration file for Decentralized Data Pipeline Dashboard

// Importing necessary dependencies
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsConfig
import io.vertx.core.Vertx
import io.vertx.kotlin.ext.web.RouterOptions
import io.vertx.kotlin.ext.web.handler.BodyHandlerOptions

// Project Config
object Config {
    const val KAFKA_BOOTSTRAP_SERVERS = "localhost:9092"
    const val KAFKA_APP_ID = "decentralized-data-pipeline"
    const val KAFKA_STREAM_THREADS = 4

    const val VERTX_PORT = 8080
    const val VERTX_HOST = "localhost"
}

// Kafka Streams Configuration
val kafkaStreamsConfig = StreamsConfig(
    mutableMapOf(
        "bootstrap.servers" to Config.KAFKA_BOOTSTRAP_SERVERS,
        "application.id" to Config.KAFKA_APP_ID,
        "num.stream.threads" to Config.KAFKA_STREAM_THREADS
    )
)

// Vert.x Configuration
val vertxOptions = RouterOptions().apply {
    port = Config.VERTX_PORT
    host = Config.VERTX_HOST
}

// Body Handler Configuration
val bodyHandlerOptions = BodyHandlerOptions().apply {
    uploadDir = "uploads"
}

// Kafka Topic Config
data class KafkaTopic(val name: String, val partitions: Int, val replicationFactor: Short)

val topics = listOf(
    KafkaTopic("raw_data", 4, 2),
    KafkaTopic("processed_data", 4, 2),
    KafkaTopic("analytics_data", 4, 2)
)

// Dashboard Routes
val routes = listOf(
    "/raw-data" to "Raw Data",
    "/processed-data" to "Processed Data",
    "/analytics-data" to "Analytics Data"
)