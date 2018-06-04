package io.jpelczar.examhelper.configuration

import org.elasticsearch.client.Client
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import java.net.InetAddress

@Configuration
@EnableElasticsearchRepositories(basePackages = arrayOf("io.jpelczar.examhelper.repository.elastic"))
class ElasticsearchConfiguration {
    @Value("\${elasticsearch.host}")
    private val host: String? = null

    @Value("\${elasticsearch.port}")
    private val port: Int = 0

    @Value("\${elasticsearch.clustername}")
    private val clusterName: String? = null

    @Bean
    fun client(): Client {
        val elasticSettings = Settings.builder().put("cluster.name", clusterName).build()
        return PreBuiltTransportClient(elasticSettings).addTransportAddress(
                InetSocketTransportAddress(InetAddress.getByName(host), port)
        )
    }

    @Bean
    fun elasticsearchTemplate(): ElasticsearchOperations = ElasticsearchTemplate(client())
}