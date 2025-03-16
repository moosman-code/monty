package bg.uni.sofia.hiker.monti.kafka.topic;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;

import bg.uni.sofia.hiker.monti.peaks.JsonFileToFeature;

public class TopicInitializer {

    private static final int COMMANDS_TOPIC_PARTITION_NUMBER = 1;
    private static final short COMMANDS_TOPIC_REPLICATION_FACTOR = 1;
    private static final String BOOTSTRAP_SERVERS = "kafka:9092";

    private final JsonFileToFeature jsonFileToFeature = new JsonFileToFeature();

    public void init() {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);

        Admin admin = Admin.create(props);

        try (AdminClient adminClient = AdminClient.create(props)) {
            Set<String> topics = adminClient.listTopics().names().get();

            for (TopicName topicName : TopicName.values()) {
                if (!topics.contains(topicName)) {
                    createCommandTopic(admin, topicName.getValue());
                    jsonFileToFeature.initTopicData(topicName);
                }
            }

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        admin.close();
    }

    private void createCommandTopic(Admin admin, String topicName) {
        admin.createTopics(Collections.singleton(
            new NewTopic(topicName, COMMANDS_TOPIC_PARTITION_NUMBER, COMMANDS_TOPIC_REPLICATION_FACTOR)
                .configs(Collections.singletonMap(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT))
        ));
    }
}

