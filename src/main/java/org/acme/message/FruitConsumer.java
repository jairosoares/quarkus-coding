package org.acme.message;

import io.smallrye.reactive.messaging.annotations.Blocking;
import org.acme.domain.Fruit;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class FruitConsumer {

    private static final Logger logger = Logger.getLogger(FruitConsumer.class);

    @Blocking
    @Incoming("fruit")
    @Transactional
    public void incomingFruit(Fruit dto) {
        Fruit fruit = new Fruit();
        fruit.name = dto.name;
        fruit.persist();
        logger.info("Não dá nenhum, mas tambem nao grava! " + fruit.name);
    }

}
