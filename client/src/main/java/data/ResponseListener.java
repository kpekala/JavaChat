package data;

import java.util.HashMap;

public interface ResponseListener {
    void success(HashMap<String,Object> response);
    void error(String message);
}
