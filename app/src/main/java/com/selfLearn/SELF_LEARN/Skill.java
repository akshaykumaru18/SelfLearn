package com.selfLearn.SELF_LEARN;
import java.io.Serializable;
public class Skill {
    private String skillName;
    private boolean skillChecked;

    public Skill(String skillName,boolean skillChecked){
        this.skillName = skillName;
        this.skillChecked = skillChecked;
    }

    public String getSkillName(){
        return skillName;
    }
    public boolean getSkillChecked(){
        return  skillChecked;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public void setSkillChecked(boolean skillChecked) {
        this.skillChecked = skillChecked;
    }

    @Override
    public String toString(){
        return this.skillName;
    }
}
