package com.iampeanut.cat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dmdw.kukps.Predict;

/**
 * Created by satjawatpanakarn on 5/2/2017 AD.
 */

public class ProblemList {
    private List<List<Problem>> problemGroup = new ArrayList<>();
    private List<String> questionName = new ArrayList<>();
    private List<List<Medicine>> medicines = new ArrayList<>();
    private Predict predict;
    private HashMap<String, String> problem = new HashMap<>();

    private static ProblemList instance;

    private ProblemList() {
        try {
            predict = new Predict();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Problem> problemsGroup1 = new ArrayList<>();
        problemsGroup1.add(new Problem("Lethargic", "เซื่องซึม", R.drawable.lethargic));
        problemsGroup1.add(new Problem("Vomit", "อาเจียน", R.drawable.vomit));
        problemsGroup1.add(new Problem("Feverish", "มีไข้", R.drawable.feverish));
        problemsGroup1.add(new Problem("Opaque_eye", "ตาขุ่น", R.drawable.opaque_eye));
        problemsGroup1.add(new Problem("dim", "ตามัว", R.drawable.dim));
        problemsGroup1.add(new Problem("Hurt_eye", "ตาเจ็บ", R.drawable.hurt_eye));
        problemsGroup1.add(new Problem("Red_eye", "ตาแดง", R.drawable.red_eye));
        problemsGroup1.add(new Problem("Problem_tear", "น้ำตาไหล", R.drawable.problem_tear));
        problemsGroup1.add(new Problem("Breath", "หายใจแรง", R.drawable.breath));
        problemsGroup1.add(new Problem("Eat_less", "เบื่ออาหาร กินอาหารน้อยลง", R.drawable.anorexia));
        problemsGroup1.add(new Problem("Hair_fall", "ขนร่วง", R.drawable.hair_fall));
        problemsGroup1.add(new Problem("seizure", "ชัก", R.drawable.seizure));

        problemsGroup1.add(new Problem("Ulcerate", "มีแผลที่ตา", "at_eye", R.drawable.ulcerate));

        List<Problem> problemsGroup2 = new ArrayList<>();
        problemsGroup2.add(new Problem("Inflammation", "แผลอักเสบที่หลอดลม", "at_windpipe", R.drawable.inflammation));
        problemsGroup2.add(new Problem("Inflammation", "แผลอักเสบที่จมูก", "at_nose", R.drawable.inflammation));


        List<Problem> problemsGroup3 = new ArrayList<>();
        problemsGroup3.add(new Problem("stabilize", "ทรงตัวไม่ได้", "unstabilize", R.drawable.stabilize));
        problemsGroup3.add(new Problem("stabilize", "เริ่มทรงตัวได้", "first_stabilize", R.drawable.stabilize));

        List<Problem> problemsGroup4 = new ArrayList<>();
        problemsGroup4.add(new Problem("itch", "คันตา", "at_eye", R.drawable.itch));
        problemsGroup4.add(new Problem("itch", "คันผิว", "at_skin", R.drawable.itch));

        List<Problem> problemsGroup5 = new ArrayList<>();
        problemsGroup5.add(new Problem("Excrete", "ท้องผูก", "constipation", R.drawable.excrete));
        problemsGroup5.add(new Problem("Excrete", "เลือดออกจากอุจจาระ", "Fecal_blood", R.drawable.excrete));


        problemGroup.add(problemsGroup1);
        problemGroup.add(problemsGroup2);
        problemGroup.add(problemsGroup3);
        problemGroup.add(problemsGroup4);
        problemGroup.add(problemsGroup5);


        questionName.add("เป็นอาการไหนบ้างครับ ?");
        questionName.add("เป็นแผลอักเสบตรงไหนครับ ?");
        questionName.add("ทรงตัวได้ไหม ?");
        questionName.add("คันตรงไหนหรือป่าวครัช ?");
        questionName.add("ขับถ่ายเป็นอย่างไรบ้างครับ ?");

        problem.put("eye", "โรคตา");
        problem.put("skin", "โรคผิวหนัง");
        problem.put("tumor", "เนื้องอก");
        problem.put("nerve", "โรคประสาท");
        problem.put("cat_flu", "ไข้หวัดแมว");
        problem.put("feline_parvovirus", "โรคไข้หัดแมว");
        problem.put("FeLV", "โรคลิวคีเมีย");
        problem.put("FIP", "โรคเยื่อบุช่องท้องอักเสบ");
        problem.put("heart_worm", "โรคพยาธิหนอนหัวใจ");
    }

    public HashMap<String, String> getProblem() {
        return problem;
    }

    public static ProblemList getInstance() {
        if (instance == null) {
            instance = new ProblemList();
        }
        return instance;
    }

    public Predict getPredict() {
        return predict;
    }

    public List<String> getQuestionList() {
        return questionName;
    }

    public List<List<Problem>> getProblemList() {
        return problemGroup;
    }
}