package com.vms.server.voc.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.vms.server.voc.dto.request.VocRequest;
import com.vms.server.voc.dto.response.VocListResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.vms.server.domain.entity.qms.QQmsVocStatus.qmsVocStatus;

public interface QmsVocStatusRepositoryCustom {

    List<VocListResponse> findVocList(VocRequest request);

    @RequiredArgsConstructor
    public class QmsVocStatusRepositoryCustomImpl implements QmsVocStatusRepositoryCustom {
        private final JPAQueryFactory jpaQueryFactory;

        @Override
        public List<VocListResponse> findVocList(VocRequest request) {
            return jpaQueryFactory.select(qmsVocStatus)
                    .from(qmsVocStatus)
                    .where(request.getDateKind().getColumn().goe(request.getStartDate()),
                            request.getDateKind().getColumn().lt(request.getEndDate()),
                            andIfNonNull(qmsVocStatus.id.systemName::eq, request.getSystem()))
                    .fetch()
                    .stream().map(VocListResponse::new)
                    .collect(Collectors.toList());
        }

        private <T> BooleanExpression andIfNonNull(Function<T, BooleanExpression> expression, T value) {
            if(value != null && !value.toString().isEmpty()) {
                return expression.apply(value);
            }

            return null;
        }
    }
}
