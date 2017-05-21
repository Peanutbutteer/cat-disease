package com.iampeanut.cat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    private static final String EXTRA_SELECT_HASHMAP = "EXTRA_SELECT_HASHMAP";

    private RecyclerView rvMedicine;
    private TextView tvName;
    private MedicineListAdapter medicineListAdapter;
    private String problemCode;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvName = (TextView) findViewById(R.id.tv_name);
        rvMedicine = (RecyclerView) findViewById(R.id.rv_medicine);
        btnBack = (Button) findViewById(R.id.btn_back);

        initInstance();
    }

    private void initInstance() {
        btnBack.setOnClickListener(v -> {
            startActivity(MainActivity.getStartIntent(ResultActivity.this));
            finish();
        });
        medicineListAdapter = new MedicineListAdapter();
        rvMedicine.setLayoutManager(new LinearLayoutManager(this));
        rvMedicine.setAdapter(medicineListAdapter);

        HashMap<String, String> problems = (HashMap<String, String>) getIntent().getSerializableExtra(EXTRA_SELECT_HASHMAP);

        try {
            String name = "";
            problemCode = ProblemList.getInstance().getPredict().unlabel(problems);
            if (ProblemList.getInstance().getProblem().containsKey(problemCode)) {
                name = ProblemList.getInstance().getProblem().get(problemCode);
            }
            tvName.setText(name);
            Iterator<Map.Entry<String, String>> it = problems.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                String key = entry.getKey();
                String value = entry.getValue();
                if (value.equals("no")) {
                    it.remove();
                }
            }

            List<String> me = getMedicine(problems);
            medicineListAdapter.setMedicines(me);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getMedicine(HashMap<String, String> problems) {
        List<String> medinceList = new ArrayList<>();
        HashMap<String, String> medicineHashMap = new HashMap<>();
        switch (problemCode) {
            case "eye":
                if (problems.containsKey("itch")) {

                }
                if (problems.containsKey("opaque_eye")) {

                }
                if (problems.containsKey("ulcerate")) {

                }
                if (problems.containsKey("hurt_eye")) {

                }
                if (problems.containsKey("dim")) {

                }
                if (problems.containsKey("red_eye")) {

                }
                if (problems.containsKey("problem_tear")) {

                }
                medicineHashMap.put("opaque_eye", "Terramycin 1 หลอด");
                medicineHashMap.put("itch", "Vigamox 1 ขวด");
                medicineHashMap.put("ulcerate", "Terramycin 1 หลอด");
                medicineHashMap.put("hurt_eye", "Vigamox 1 ขวด");
                medicineHashMap.put("dim", "Vigamox 1 ขวด");
                medicineHashMap.put("red_eye", "Atropine 1 ขวด");
                medicineHashMap.put("problem_tear", "Atropine 1 ขวด");
                break;
            case "skin":
                medicineHashMap.put("hair_fall", "Dermaform)");
                medicineHashMap.put("itch", "Dermaform");
                medicineHashMap.put("eat_less", "Ferric Plus-K");
                break;
            case "tumor":
                medicineHashMap.put("breath", "Tolfeeline (60)");
                medicineHashMap.put("eat_less", "Ferric Plus-K");
            case "nerve":
                medicineHashMap.put("excrete", "Emrofloxacin");
                medicineHashMap.put("eat_less", "Ferric Plus-K");
                medicineHashMap.put("stabilize", "Vitamin B Complex");
                break;
            case "cat_flu":
                medicineHashMap.put("feverish", "Tolfeeline (60)");
                break;
            case "Feline_parvovirus":
                medicineHashMap.put("lethargic", "Ferric Plus-K");
                medicineHashMap.put("eat_less", "Ferric Plus-K");
                medicineHashMap.put("feverish", "Tolfeeline (60)");
                break;
            case "FeLV":
                medicineHashMap.put("excrete", "Hemo Maxx");
                break;
            case "FIP":
                medicineHashMap.put("seizure", "Diazepam");
                medicineHashMap.put("eat_less", "Ferric Plus-K");
                medicineHashMap.put("stabilize", "Vitamin B Complex");
                medicineHashMap.put("feverish", "Tolfeeline (60)");
                break;
            case "heart_worm":
                medicineHashMap.put("lethargic", "Ferric Plus-K");
                medicineHashMap.put("vomit", "Disento");
                medicineHashMap.put("seizure", "Diazepam");
                break;
        }
        return AddName(problems, medicineHashMap);
    }

    private List<String> AddName(HashMap<String, String> problems, HashMap<String, String> medicineHashMap) {
        List<String> medinceList = new ArrayList<>();
        for (Map.Entry<String, String> entry : medicineHashMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (problems.containsKey(key)) {
                medinceList.add(value);
            }
        }
        return medinceList;
    }


    public static Intent getStartIntent(Context context, HashMap<String, String> stringHashMap) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(EXTRA_SELECT_HASHMAP, stringHashMap);
        return intent;
    }


}
