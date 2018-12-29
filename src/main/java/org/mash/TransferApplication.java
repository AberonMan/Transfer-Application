package org.mash;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;
import org.mash.repository.InMemoryAccountRepository;
import org.mash.repository.api.AccountRepository;
import org.mash.resources.AccountResource;
import org.mash.resources.TransferResource;
import org.mash.service.AccountService;
import org.mash.service.TransferService;

public class TransferApplication extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new TransferApplication().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        AccountRepository repository = new InMemoryAccountRepository();
        TransferResource transferController = new TransferResource(new TransferService(repository));
        AccountResource accountController = new AccountResource(new AccountService(repository));

        JerseyEnvironment jerseyEnvironment = environment.jersey();
        jerseyEnvironment.register(transferController);
        jerseyEnvironment.register(accountController);

    }
}

