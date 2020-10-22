package com.batch.cb.cb.crw.roll.character.output;

import com.batch.cb.cb.util.crw.CrwOutput;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class RollCampOutput implements CrwOutput<List<String>, String> {

    @Override
    public List<String> getOutput(String data) {
        Document doc = Jsoup.parse(data);
        Elements els = doc.select("div.champion-index__champion-item");
        return els.stream().map(a->a.select("a").attr("href")).collect(Collectors.toList());
    }

}
