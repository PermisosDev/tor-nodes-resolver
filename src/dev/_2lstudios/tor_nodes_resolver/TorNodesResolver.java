package dev._2lstudios.tor_nodes_resolver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TorNodesResolver {

    private Set<String> nodes = new HashSet<>();

    private static String fallbackurl = "https://check.torproject.org/torbulkexitlist";

    public TorNodesResolver () {

        try {
            final String content = getNodeListFromUrl();
            final String[] list = content.split("\n");

            nodes.addAll(Arrays.asList(list));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TorNodesResolver (String nodesUrl)  {

        try {
            final String content = getNodeListFromUrl(nodesUrl);
            final String[] list = content.split("\n");

            nodes.addAll(List.of(list));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getNodeListFromUrl() throws IOException {
        try (Scanner scanner = new Scanner(new URL(fallbackurl).openStream(), StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    private static String getNodeListFromUrl(String requestURL) throws IOException {
        try (Scanner scanner = new Scanner(new URL(requestURL).openStream(), StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    public boolean isNode(final String address) {
        Optional<String> result = this.nodes.stream().parallel().filter(e -> e.equals(address)).findAny();
        return result.isPresent();
    }

    public boolean isNode(final InetSocketAddress socketAddress) {
        Optional<String> result = this.nodes.stream().parallel().filter(e -> e.equals(socketAddress.toString())).findAny();
        return result.isPresent();
    }

}
