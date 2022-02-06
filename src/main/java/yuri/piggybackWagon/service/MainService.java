package yuri.piggybackWagon.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainService {

    /*public static void main(String[] args) {

        int max = 0;

        for (int i = 1; i < reserveSum.length; i++) {
            if (piggyback8[1] + ((37 + kyung + 1) * 13) + ((37 + jung) * 2) < i) break; // 피기백 마지막 8번 열차가 64만km 도착하면 종료

            if (max < reserveSum[i]) max = reserveSum[i];

            System.out.print((i < 10 ? "0" + i : i) + "일: " + reserveSum[i] + "대 ");

            if (i % 10 == 0) {
                System.out.println("\n");
            }
        }

        System.out.println("\n\n최댓값 = " + max + "대");
    }*/

    public List<Map<String, Object>> findReserveDateBykyungAndJung(int kyung, int jung) {
        int[] piggyback1 = new int[1001];
        piggyback1[0] = 31;
        piggyback1[1] = 31;

        int[] piggyback2 = new int[1001];
        piggyback2[0] = 33;
        piggyback2[1] = piggyback1[1] + kyung + 1;

        int[] piggyback3 = new int[1001];
        piggyback3[0] = 37;
        piggyback3[1] = piggyback2[1] + kyung + 1;

        int[] piggyback4 = new int[1001];
        piggyback4[0] = 37;
        piggyback4[1] = piggyback3[1] + kyung + 1;

        int[] piggyback5 = new int[1001];
        piggyback5[0] = 37;
        piggyback5[1] = piggyback4[1] + kyung + 1;

        int[] piggyback6 = new int[1001];
        piggyback6[0] = 37;
        piggyback6[1] = piggyback5[1] + kyung + 1;

        int[] piggyback7 = new int[1001];
        piggyback7[0] = 37;
        piggyback7[1] = piggyback6[1] + kyung + 1;

        int[] piggyback8 = new int[1001];
        piggyback8[0] = 37;
        piggyback8[1] = piggyback7[1] + kyung + 1;


        int[] reserveSum = new int[1001]; // 일자 별로 필요한 피기백 수량의 합

        for (int i = 1; i < 17; i++) {
            int startDay1 = piggyback1[i];
            int startDay2 = piggyback2[i];
            int startDay3 = piggyback3[i];
            int startDay4 = piggyback4[i];
            int startDay5 = piggyback5[i];
            int startDay6 = piggyback6[i];
            int startDay7 = piggyback7[i];
            int startDay8 = piggyback8[i];

            if (i % 8 == 0) { // 중정비 맡겨야 할 구간
                for (int j = 0; j < jung; j++) {
                    reserveSum[startDay1++] += 20;
                    reserveSum[startDay2++] += 20;
                    reserveSum[startDay3++] += 20;
                    reserveSum[startDay4++] += 20;
                    reserveSum[startDay5++] += 20;
                    reserveSum[startDay6++] += 20;
                    reserveSum[startDay7++] += 20;
                    reserveSum[startDay8++] += 20;
                }
                piggyback1[i + 1] = piggyback1[i] + piggyback1[0] + jung;
                piggyback2[i + 1] = piggyback2[i] + piggyback2[0] + jung;
                piggyback3[i + 1] = piggyback3[i] + piggyback3[0] + jung;
                piggyback4[i + 1] = piggyback4[i] + piggyback4[0] + jung;
                piggyback5[i + 1] = piggyback5[i] + piggyback5[0] + jung;
                piggyback6[i + 1] = piggyback6[i] + piggyback6[0] + jung;
                piggyback7[i + 1] = piggyback7[i] + piggyback7[0] + jung;
                piggyback8[i + 1] = piggyback8[i] + piggyback8[0] + jung;
            } else {
                for (int j = 0; j < kyung + 1; j++) { // 경정비 일 동안 필요한 피기백 수량 넣기
                    if (j == kyung) {
                        reserveSum[startDay1++] += 10;
                        reserveSum[startDay2++] += 10;
                        reserveSum[startDay3++] += 10;
                        reserveSum[startDay4++] += 10;
                        reserveSum[startDay5++] += 10;
                        reserveSum[startDay6++] += 10;
                        reserveSum[startDay7++] += 10;
                        reserveSum[startDay8++] += 10;
                    } else {
                        reserveSum[startDay1++] += 20;
                        reserveSum[startDay2++] += 20;
                        reserveSum[startDay3++] += 20;
                        reserveSum[startDay4++] += 20;
                        reserveSum[startDay5++] += 20;
                        reserveSum[startDay6++] += 20;
                        reserveSum[startDay7++] += 20;
                        reserveSum[startDay8++] += 20;
                    }
                }
                piggyback1[i + 1] = piggyback1[i] + piggyback1[0] + kyung + 1;
                piggyback2[i + 1] = piggyback2[i] + piggyback2[0] + kyung + 1;
                piggyback3[i + 1] = piggyback3[i] + piggyback3[0] + kyung + 1;
                piggyback4[i + 1] = piggyback4[i] + piggyback4[0] + kyung + 1;
                piggyback5[i + 1] = piggyback5[i] + piggyback5[0] + kyung + 1;
                piggyback6[i + 1] = piggyback6[i] + piggyback6[0] + kyung + 1;
                piggyback7[i + 1] = piggyback7[i] + piggyback7[0] + kyung + 1;
                piggyback8[i + 1] = piggyback8[i] + piggyback8[0] + kyung + 1;
            }
        }
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> resultMap;

        for (int i = 1; i < reserveSum.length; i++) {
            if (piggyback8[1] + ((37 + kyung + 1) * 13) + ((37 + jung) * 2) < i) break; // 피기백 마지막 8번 열차가 64만km 도착하면 종료

            resultMap = new HashMap<>();
            resultMap.put("recid", i);
            resultMap.put("reserveSum", reserveSum[i]);
            resultList.add(resultMap);
        }

        return resultList;
    }
}
