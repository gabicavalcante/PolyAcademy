package polytech.unice.fr.polynews.fragment.info;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import polytech.unice.fr.polynews.R;

/**
 * @version 20/04/16.
 */
public class PolytechFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.about_polytech, container, false);
        WebView web = (WebView) v.findViewById(R.id.polytech_webView);
        web.loadUrl("https://polytechsi3.wordpress.com");
        return v;
    }
}