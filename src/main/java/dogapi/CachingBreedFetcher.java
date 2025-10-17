package dogapi;

import java.util.*;

/**
 * This BreedFetcher caches fetch request results to improve performance and
 * lessen the load on the underlying data source. An implementation of BreedFetcher
 * must be provided. The number of calls to the underlying fetcher are recorded.
 *
 * If a call to getSubBreeds produces a BreedNotFoundException, then it is NOT cached
 * in this implementation. The provided tests check for this behaviour.
 *
 * The cache maps the name of a breed to its list of sub breed names.
 */
public class CachingBreedFetcher implements BreedFetcher {
    // TODO Task 2: Complete this class
    private int callsMade = 0;
    DogApiBreedFetcher fetcher = new DogApiBreedFetcher();
    Map<String, List<String>> cachedCall = new HashMap<>();
    public CachingBreedFetcher(BreedFetcher fetcher) {

    }

    @Override
    public List<String> getSubBreeds(String breed) {
        // return statement included so that the starter code can compile and run.
        if (cachedCall.containsKey(breed)) {
            return cachedCall.get(breed);

        } else {
            List<String> subbreeds = fetcher.getSubBreeds(breed);
            cachedCall.put(breed, subbreeds);
            return subbreeds;
        }
    }

    public int getCallsMade() {
        return callsMade;
    }
}