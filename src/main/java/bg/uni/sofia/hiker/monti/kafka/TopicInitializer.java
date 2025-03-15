package bg.uni.sofia.hiker.monti.kafka;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;

import java.util.Collections;
import java.util.Properties;

public class TopicInitializer {

    private static final int COMMANDS_TOPIC_PARTITION_NUMBER = 1;
    private static final short COMMANDS_TOPIC_REPLICATION_FACTOR = 1;

    public void init() {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        Admin admin = Admin.create(props);

        for (TopicName topicName : TopicName.values()) {
            System.out.println("hello");
            createCommandTopic(admin, topicName.getValue());
        }

        admin.close();
    }

    private CreateTopicsResult createCommandTopic(Admin admin, String topicName) {
        return admin.createTopics(Collections.singleton(
            new NewTopic(topicName, COMMANDS_TOPIC_PARTITION_NUMBER, COMMANDS_TOPIC_REPLICATION_FACTOR)
                .configs(Collections.singletonMap(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT))
        ));
    }
}
