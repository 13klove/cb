package com.batch.cb.cb.crw.roll.characterDetail.output;

import com.batch.cb.cb.domain.roll.character.entity.RollCharacter;
import com.batch.cb.cb.domain.roll.position.PositionType;
import com.batch.cb.cb.domain.roll.position.entity.RollPosition;
import com.batch.cb.cb.domain.roll.skill.SkillType;
import com.batch.cb.cb.domain.roll.skill.entity.RollSkill;
import com.batch.cb.cb.util.crw.CrwOutput;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class RollCampDetailOutPut implements CrwOutput<RollCharacter, String> {

    @Override
    public RollCharacter getOutput(String data) {
        Document doc = Jsoup.parse(data);

        String name = doc.select("h1.champion-stats-header-info__name").text();
        String tier = doc.select("div.champion-stats-header-info__tier b").text().replace("티어", "");
        RollCharacter rollCharacter = RollCharacter.createRollCharacter(name, Integer.valueOf(tier.trim()));

        Elements positionElement = doc.select("li.champion-stats-header__position");
        List<RollPosition> rollPositions = positionElement.stream()
                .map(a -> RollPosition.createRollPosition(PositionType.valueOf(a.attr("data-position"))))
                .collect(Collectors.toList());
        rollCharacter.addRollPositions(rollPositions);

        Elements skillElement = doc.select("div.champion-stat__skill");
        List<RollSkill> rollSkills = skillElement.stream().map(a -> {
            Document skillDoc = Jsoup.parse(a.attr("title"));
            return RollSkill.createRollSkill(skillDoc.select("b").text(), SkillType.keyValudOf(a.select("span.key").text()));
        }).collect(Collectors.toList());
        rollCharacter.addRollSkills(rollSkills);

        return rollCharacter;
    }

}
