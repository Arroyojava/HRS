create table addresses
(
    addressid             bigint auto_increment
        primary key,
    apartment_unit_number varchar(255) null,
    city                  varchar(255) not null,
    country               varchar(255) not null,
    state                 varchar(255) not null,
    street                varchar(255) not null,
    zip_code              varchar(255) not null
)
    engine = InnoDB;

create table customers
(
    customerid   bigint auto_increment
        primary key,
    email        varchar(255) not null,
    first_name   varchar(255) not null,
    last_name    varchar(255) not null,
    phone_number varchar(255) not null,
    preferences  varchar(255) null,
    address_id   bigint       not null,
    constraint FKm07rwxbyxjv8r4fcye1ff6rmq
        foreign key (address_id) references addresses (addressid)
)
    engine = InnoDB;

create table design_installation_services
(
    serviceid   bigint auto_increment
        primary key,
    hourly_rate double       not null,
    min_charge  double       not null,
    type        varchar(255) not null
)
    engine = InnoDB;

create table locations
(
    locationid   bigint auto_increment
        primary key,
    name         varchar(255) not null,
    phone_number varchar(255) not null,
    address_id   bigint       not null,
    constraint FKd0253cj4ou9w19m1vwbitcqme
        foreign key (address_id) references addresses (addressid)
)
    engine = InnoDB;

create table conferences
(
    conferenceid bigint auto_increment
        primary key,
    date         date         not null,
    type         varchar(255) not null,
    location_id  bigint       not null,
    constraint FKovuq89052rdgpjyo6p8fjpix4
        foreign key (location_id) references locations (locationid)
)
    engine = InnoDB;

create table employees
(
    employeeid   bigint auto_increment
        primary key,
    email        varchar(255) not null,
    first_name   varchar(255) not null,
    last_name    varchar(255) not null,
    phone_number varchar(255) not null,
    position     varchar(255) not null,
    skills       varchar(255) not null,
    address_id   bigint       not null,
    location_id  bigint       not null,
    constraint FKirh0a5q2en4gkbayqhgfiwhs
        foreign key (location_id) references locations (locationid),
    constraint FKkvaekcr8qinc0oo3kxwxgtyt9
        foreign key (address_id) references addresses (addressid)
)
    engine = InnoDB;

create table orders
(
    orderid              bigint auto_increment
        primary key,
    delivery_charge      double       null,
    end_date             date         null,
    installation_charge  double       null,
    start_date           date         not null,
    status               varchar(255) not null,
    total_price          double       not null,
    assigned_employee_id bigint       null,
    customer_id          bigint       not null,
    constraint FKnnpqx7m6ph0tlp1r49tdt23h6
        foreign key (assigned_employee_id) references employees (employeeid),
    constraint FKpxtb8awmi0dk6smoh2vp1litg
        foreign key (customer_id) references customers (customerid)
)
    engine = InnoDB;

create table deliveries
(
    deliveryid      bigint auto_increment
        primary key,
    delivery_date   date         not null,
    delivery_status varchar(255) not null,
    order_id        bigint       not null,
    constraint FK7isx0rnbgqr1dcofd5putl6jw
        foreign key (order_id) references orders (orderid)
)
    engine = InnoDB;

create table products_services
(
    product_serviceid bigint auto_increment
        primary key,
    availability      bit          not null,
    category          varchar(255) not null,
    description       varchar(255) not null,
    image_url         varchar(255) null,
    name              varchar(255) not null,
    price             double       not null
)
    engine = InnoDB;

create table appliances
(
    applianceid        bigint auto_increment
        primary key,
    brand              varchar(255) not null,
    delivery_method    varchar(255) not null,
    model              varchar(255) not null,
    website            varchar(255) not null,
    product_service_id bigint       not null,
    constraint FKbda7wcn0etg9oqbl672xumq1v
        foreign key (product_service_id) references products_services (product_serviceid)
)
    engine = InnoDB;

create table bathroom_fixtures
(
    fixtureid          bigint auto_increment
        primary key,
    brand              varchar(255) not null,
    category           varchar(255) not null,
    model              varchar(255) not null,
    website            varchar(255) not null,
    product_service_id bigint       not null,
    constraint FKo4abr54vvksgy9kcgct6xvlia
        foreign key (product_service_id) references products_services (product_serviceid)
)
    engine = InnoDB;

create table cabinets
(
    cabinetid          bigint auto_increment
        primary key,
    brand              varchar(255) not null,
    model              varchar(255) not null,
    phone              varchar(255) not null,
    website            varchar(255) not null,
    address_id         bigint       not null,
    product_service_id bigint       not null,
    constraint FK4n61x6723a7dq6hkfi3pldys2
        foreign key (product_service_id) references products_services (product_serviceid),
    constraint FK5wb62fe53ys1l9xy98bbyqjgd
        foreign key (address_id) references addresses (addressid)
)
    engine = InnoDB;

create table countertops_tiles
(
    countertop_tileid  bigint auto_increment
        primary key,
    brand              varchar(255) not null,
    material_type      varchar(255) not null,
    model              varchar(255) not null,
    website            varchar(255) not null,
    product_service_id bigint       not null,
    constraint FKj7abu8ntyu98r8o79h14wyxf9
        foreign key (product_service_id) references products_services (product_serviceid)
)
    engine = InnoDB;

create table inventory
(
    inventoryid        bigint auto_increment
        primary key,
    cost               double       not null,
    location           varchar(255) not null,
    quantity           int          not null,
    product_service_id bigint       not null,
    constraint FKkioetaxign9dvsdldxie5mqqg
        foreign key (product_service_id) references products_services (product_serviceid)
)
    engine = InnoDB;

create table lighting
(
    lightingid         bigint auto_increment
        primary key,
    brand              varchar(255) not null,
    model              varchar(255) not null,
    website            varchar(255) not null,
    product_service_id bigint       not null,
    constraint FK7u3g4ilyaalxsgg313393v4wc
        foreign key (product_service_id) references products_services (product_serviceid)
)
    engine = InnoDB;

create table order_products_services
(
    order_id           bigint not null,
    product_service_id bigint not null,
    primary key (order_id, product_service_id),
    constraint FK3o0sboci9oq9jsifj8tqlr3j2
        foreign key (product_service_id) references products_services (product_serviceid),
    constraint FKfbgtdnb7ytwoyakrghhrnstjl
        foreign key (order_id) references orders (orderid)
)
    engine = InnoDB;

create table plumbing
(
    plumbingid         bigint auto_increment
        primary key,
    brand              varchar(255) not null,
    model              varchar(255) not null,
    website            varchar(255) not null,
    product_service_id bigint       not null,
    constraint FK9lka2gj7dxetc2dwjo4fqan5a
        foreign key (product_service_id) references products_services (product_serviceid)
)
    engine = InnoDB;

create table service_products_services
(
    service_id         bigint not null,
    product_service_id bigint not null,
    primary key (service_id, product_service_id),
    constraint FK7igw9oasghpgtttfeqjcuwks9
        foreign key (service_id) references design_installation_services (serviceid),
    constraint FKgukvmh4c990fgwpovuysyck6e
        foreign key (product_service_id) references products_services (product_serviceid)
)
    engine = InnoDB;

create table suppliers
(
    supplierid                 bigint auto_increment
        primary key,
    contact_info               varchar(255) not null,
    name                       varchar(255) not null,
    products_services_supplied varchar(255) not null,
    terms_of_cooperation       varchar(255) not null
)
    engine = InnoDB;

create table supplier_products_services
(
    supplier_id        bigint not null,
    product_service_id bigint not null,
    primary key (supplier_id, product_service_id),
    constraint FK5t71hcjnyixyq4d97vkti2k49
        foreign key (product_service_id) references products_services (product_serviceid),
    constraint FKp5nyg2pjml77yo279jq6jf55c
        foreign key (supplier_id) references suppliers (supplierid)
)
    engine = InnoDB;


