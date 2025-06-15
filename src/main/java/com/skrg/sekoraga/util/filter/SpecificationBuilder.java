package com.skrg.sekoraga.util.filter;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationBuilder<T> {

    // Membuat filter berdasarkan tipe range (Integer, Long, dll.)
    public <X> Specification<T> buildRangeSpecification(Filter<X> filter, String field) {
        return (root, query, criteriaBuilder) -> {
            if (filter.getEquals() != null) {
                return criteriaBuilder.equal(root.get(field), filter.getEquals());
            } else if (filter.getIn() != null) {
                return root.get(field).in(filter.getIn());
            } else if (filter.getNotEquals() != null) {
                return criteriaBuilder.notEqual(root.get(field), filter.getNotEquals());
            } else if (filter.getSpecified() != null) {
                return filter.getSpecified() ? criteriaBuilder.isNotNull(root.get(field))
                        : criteriaBuilder.isNull(root.get(field));
            }
            return null;
        };
    }

    // Membuat filter berdasarkan string
    public Specification<T> buildStringSpecification(StringFilter filter, String field) {
        return (root, query, criteriaBuilder) -> {
            if (filter.getEquals() != null) {
                return criteriaBuilder.equal(root.get(field), filter.getEquals());
            } else if (filter.getIn() != null) {
                return root.get(field).in(filter.getIn());
            } else if (filter.getNotIn() != null) {
                return criteriaBuilder.not(root.get(field).in(filter.getNotIn()));
            } else if (filter.getNotEquals() != null) {
                return criteriaBuilder.notEqual(root.get(field), filter.getNotEquals());
            } else if (filter.getSpecified() != null) {
                return filter.getSpecified() ? criteriaBuilder.isNotNull(root.get(field))
                        : criteriaBuilder.isNull(root.get(field));
            } else if (filter.getContains() != null) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(field)),
                        "%" + filter.getContains().toLowerCase() + "%");
            } else if (filter.getDoesNotContain() != null) {
                return criteriaBuilder.notLike(criteriaBuilder.lower(root.get(field)),
                        "%" + filter.getDoesNotContain().toLowerCase() + "%");
            }
            return null;
        };
    }

    // Membuat filter umum (untuk tipe data lain)
    public <X> Specification<T> buildSpecification(Filter<X> filter, String field) {
        return (root, query, criteriaBuilder) -> {
            if (filter.getEquals() != null) {
                return criteriaBuilder.equal(root.get(field), filter.getEquals());
            } else if (filter.getIn() != null) {
                return root.get(field).in(filter.getIn());
            } else if (filter.getNotEquals() != null) {
                return criteriaBuilder.notEqual(root.get(field), filter.getNotEquals());
            } else if (filter.getSpecified() != null) {
                return filter.getSpecified() ? criteriaBuilder.isNotNull(root.get(field))
                        : criteriaBuilder.isNull(root.get(field));
            }
            return null;
        };
    }

    // filter dengan LEFT JOIN
    public <X> Specification<T> buildLeftJoinSpecification(Filter<X> filter, String field, String joinField) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> join = root.join(joinField, JoinType.LEFT);

            // 1. Equals
            if (filter.getEquals() != null) {
                return criteriaBuilder.equal(join.get(field), filter.getEquals());
            }

            // 2. NotEquals
            if (filter.getNotEquals() != null) {
                return criteriaBuilder.notEqual(join.get(field), filter.getNotEquals());
            }

            // 3. Specified (true or false)
            if (filter.getSpecified() != null) {
                if (filter.getSpecified()) {
                    return criteriaBuilder.isNotNull(join.get(field));
                } else {
                    return criteriaBuilder.isNull(join.get(field));
                }
            }

            // 4. In
            if (filter.getIn() != null && !filter.getIn().isEmpty()) {
                return join.get(field).in(filter.getIn());
            }

            // 5. NotIn
            if (filter.getNotIn() != null && !filter.getNotIn().isEmpty()) {
                return criteriaBuilder.not(join.get(field).in(filter.getNotIn()));
            }

            // 6. Contains (LIKE '%value%')
            if (filter.getContains() != null) {
                return criteriaBuilder.like(criteriaBuilder.lower(join.get(field)),
                        "%" + filter.getContains().toString().toLowerCase() + "%");
            }

            // 7. DoesNotContain (NOT LIKE '%value%')
            if (filter.getDoesNotContain() != null) {
                return criteriaBuilder.notLike(join.get(field), "%" + filter.getDoesNotContain() + "%");
            }

            // Default case: No filter
            return criteriaBuilder.conjunction();
        };
    }

    // filter dengan RIGHT JOIN
    public <X> Specification<T> buildRightJoinSpecification(Filter<X> filter, String field, String joinField) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> join = root.join(joinField, JoinType.RIGHT);

            // 1. Equals
            if (filter.getEquals() != null) {
                return criteriaBuilder.equal(join.get(field), filter.getEquals());
            }

            // 2. NotEquals
            if (filter.getNotEquals() != null) {
                return criteriaBuilder.notEqual(join.get(field), filter.getNotEquals());
            }

            // 3. Specified (true or false)
            if (filter.getSpecified() != null) {
                if (filter.getSpecified()) {
                    return criteriaBuilder.isNotNull(join.get(field));
                } else {
                    return criteriaBuilder.isNull(join.get(field));
                }
            }

            // 4. In
            if (filter.getIn() != null && !filter.getIn().isEmpty()) {
                return join.get(field).in(filter.getIn());
            }

            // 5. NotIn
            if (filter.getNotIn() != null && !filter.getNotIn().isEmpty()) {
                return criteriaBuilder.not(join.get(field).in(filter.getNotIn()));
            }

            // 6. Contains (LIKE '%value%')
            if (filter.getContains() != null) {
                return criteriaBuilder.like(join.get(field), "%" + filter.getContains() + "%");
            }

            // 7. DoesNotContain (NOT LIKE '%value%')
            if (filter.getDoesNotContain() != null) {
                return criteriaBuilder.notLike(join.get(field), "%" + filter.getDoesNotContain() + "%");
            }

            // Default case: No filter
            return criteriaBuilder.conjunction();
        };
    }

    // Membuat filter dengan INNER JOIN
    public <X> Specification<T> buildInnerJoinSpecification(Filter<X> filter, String field, String joinField) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> join = root.join(joinField, JoinType.INNER);

            // 1. Equals
            if (filter.getEquals() != null) {
                return criteriaBuilder.equal(join.get(field), filter.getEquals());
            }

            // 2. NotEquals
            if (filter.getNotEquals() != null) {
                return criteriaBuilder.notEqual(join.get(field), filter.getNotEquals());
            }

            // 3. Specified (true or false)
            if (filter.getSpecified() != null) {
                if (filter.getSpecified()) {
                    return criteriaBuilder.isNotNull(join.get(field));
                } else {
                    return criteriaBuilder.isNull(join.get(field));
                }
            }

            // 4. In
            if (filter.getIn() != null && !filter.getIn().isEmpty()) {
                return join.get(field).in(filter.getIn());
            }

            // 5. NotIn
            if (filter.getNotIn() != null && !filter.getNotIn().isEmpty()) {
                return criteriaBuilder.not(join.get(field).in(filter.getNotIn()));
            }

            // 6. Contains (LIKE '%value%')
            if (filter.getContains() != null) {
                return criteriaBuilder.like(join.get(field), "%" + filter.getContains() + "%");
            }

            // 7. DoesNotContain (NOT LIKE '%value%')
            if (filter.getDoesNotContain() != null) {
                return criteriaBuilder.notLike(join.get(field), "%" + filter.getDoesNotContain() + "%");
            }

            // Default case: No filter
            return criteriaBuilder.conjunction();
        };
    }

    // Membuat filter dengan Dynamic Join
    public <X> Specification<T> buildDynamicJoinSpecification(Filter<X> filter, String joinPath, String field,
            JoinType joinType) {
        return (root, query, criteriaBuilder) -> {
            String[] joinPaths = joinPath.split("\\.");
            From<?, ?> join = root;

            for (String path : joinPaths) {
                join = join.join(path, joinType);
            }

            return createJoinFilter(criteriaBuilder, filter, join, field);
        };
    }

    // Utilitas untuk membuat filter berdasarkan Join
    private <X> Predicate createJoinFilter(CriteriaBuilder criteriaBuilder, Filter<X> filter, Path<?> join,
            String field) {
        if (filter.getEquals() != null) {
            return criteriaBuilder.equal(join.get(field), filter.getEquals());
        } else if (filter.getIn() != null) {
            return join.get(field).in(filter.getIn());
        } else if (filter.getNotEquals() != null) {
            return criteriaBuilder.notEqual(join.get(field), filter.getNotEquals());
        } else if (filter.getSpecified() != null) {
            return filter.getSpecified() ? criteriaBuilder.isNotNull(join.get(field))
                    : criteriaBuilder.isNull(join.get(field));
        }
        return null;
    }
}
