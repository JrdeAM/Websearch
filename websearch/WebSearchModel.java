import java.io.*;
import java.util.ArrayList;
import java.util.List;
import interfaces.QueryFilter;
import interfaces.QueryObserver;
import models.FilteredObserver;

/**
 * Perform "web search" (from a file), notify the interested observers of each
 * query.
 */
public class WebSearchModel {
    private final File sourceFile;
    private final List<FilteredObserver> observers = new ArrayList<>();

    public WebSearchModel(File sourceFile) {
        this.sourceFile = sourceFile;

    }

    public void pretendToSearch() {
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                notifyAllObservers(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addQueryObserver(QueryObserver observer, QueryFilter filter) {
        observers.add(new FilteredObserver(observer, filter));
    }

    private void notifyAllObservers(String line) {
        for (FilteredObserver obs : observers) {

            if (obs.accepts(line)) {
                obs.notify(line);
            }

        }
    }
}
