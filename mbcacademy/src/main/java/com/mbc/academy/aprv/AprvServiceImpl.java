package com.mbc.academy.aprv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AprvServiceImpl implements AprvService{
	
	@Autowired
	AprvDAO aprvDao;
	
	@Override
	public List<AprvDTO> aprvList(AprvCriteria cri) throws Exception {
		System.out.println("결재 리스트 조회 Service");
		return aprvDao.aprvList(cri);
	}
	
	@Override
	public List<AprvDTO> aprvListMain(AprvCriteria cri) throws Exception {
		return aprvDao.aprvListMain(cri);
	}
	
	@Override
	public int getTotalCountAprv(AprvCriteria cri) throws Exception {
		return aprvDao.getTotalCountAprv(cri);
	}
	
	public List<AprvDTO> ptoList(AprvCriteria cri) throws Exception {
		System.out.println("신청 리스트 조회 Service");
		return aprvDao.ptoList(cri);
	}
	
	@Override
	public List<AprvDTO> ptoListMain(AprvCriteria cri) throws Exception {
		return aprvDao.ptoListMain(cri);
	}
	
	@Override
	public int getTotalCountPto(AprvCriteria cri) throws Exception {
		return aprvDao.getTotalCountPto(cri);
	}

	@Override
	public int ptoApply(AprvDTO avo) throws Exception {
		return aprvDao.ptoApply(avo);
	}

	@Override
	public AprvDTO aprvDetail(int pno) throws Exception {
		System.out.println(pno + "번 결재 상세 조회 Service");
		return aprvDao.aprvDetail(pno);
	}

	@Override
	public int ptoModify(AprvDTO avo) throws Exception {
		return aprvDao.ptoModify(avo);
	}

	@Override
	public int ptoDel(int pno) throws Exception {
		return aprvDao.ptoDel(pno);
	}

	@Override
	@Transactional
	public int aprv(AprvDTO aprv) throws Exception {
		System.out.println(aprv.getPno() + "번 결재 Service");
		
		int result = aprvDao.aprv(aprv);
		
		if ("P2".equals(aprv.getStts())) {
			if ("T0".equals(aprv.getPtotp())) {
				System.out.println(aprv.getEmpno() + "연차 업데이트 Service");
				aprvDao.updatePTO(aprv);
				aprvDao.insertATST(aprv);
			} else if ("T1".equals(aprv.getPtotp())) {
				System.out.println(aprv.getEmpno() + "반차 업데이트 Service");
				aprvDao.updateHalfPTO(aprv);
				aprvDao.insertATST(aprv);
			} else if ("T2".equals(aprv.getPtotp())) {
				System.out.println(aprv.getEmpno() + "기타 업데이트 Service");
				aprvDao.insertATST(aprv);
			}
		}
		return result;
	}
	
	@Override
	public AprvDTO getApplyInfo(String empno) throws Exception {
		return aprvDao.getApplyInfo(empno);
	}

}
