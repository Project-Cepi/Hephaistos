package mca;

import org.jglrxavpok.hephaistos.data.RandomAccessFileSource;
import org.jglrxavpok.hephaistos.data.DataSource;
import org.jglrxavpok.hephaistos.data.GrowableSource;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.stream.Stream;

public class DataSourceProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        DataSource growable = new GrowableSource();
        File target = new File(""+context.getUniqueId().hashCode()+".mca");
        target.createNewFile();
        target.deleteOnExit();
        DataSource raf = new RandomAccessFileSource(new RandomAccessFile(target, "rw"));

        return Stream.of(
            Arguments.of(raf),
            Arguments.of(growable)
        );
    }
}
