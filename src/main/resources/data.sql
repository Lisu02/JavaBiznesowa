
--DELETE FROM users WHERE username IN ('user', 'admin');


-- Inserting users data
 TRUNCATE TABLE users,authorities,advert,category;
-- usuwac zawartosc tabel wazne!!!!

INSERT INTO users (username, enabled, password) VALUES
('user', true, '{noop}test123'),
('user2', true, '{noop}test123'),
('admin', true, '{noop}test123');


INSERT INTO authorities (authority, username) VALUES
('ROLE_EMPLOYEE', 'user'),
('ROLE_EMPLOYEE', 'user2'),
('ROLE_ADMIN', 'admin');

INSERT INTO category (name) VALUES ('sprzedaż'), ('wynajem'), ('zagubione'), ('poszukiwanie pracy');



INSERT INTO advert (title, information, accepted, username, creation_date, expiration_date, category_id)
VALUES
    ('Sprzedam Rower Górski',
     'Sprzedam rower górski marki Giant, model XTR. Rower jest w bardzo dobrym stanie, mało używany, regularnie serwisowany w autoryzowanym serwisie. Wyposażony w 21 biegów, amortyzatory oraz nowe opony. Idealny na wyprawy w trudnym terenie. Cena: 800 zł. Możliwość obejrzenia w weekendy po wcześniejszym umówieniu. Kontakt: 123-456-789.',
     true,
     'user2',
     '2024-07-04',
     '2024-08-04',
     (SELECT id FROM category WHERE name = 'sprzedaż')),

    ('Mieszkanie do Wynajęcia',
     'Do wynajęcia dwupokojowe mieszkanie w centrum miasta, ul. Krakowska 12. Mieszkanie jest w pełni umeblowane, świeżo po remoncie. Składa się z przestronnego salonu, sypialni, kuchni oraz łazienki. Blisko przystanki komunikacji miejskiej, sklepy, restauracje oraz park. Cena: 2000 zł/miesiąc, plus media. Preferowany najem długoterminowy. Kontakt: mieszkanie@domena.pl.',
     true,
     'user2',
     '2024-07-05',
     '2024-08-05',
     (SELECT id FROM category WHERE name = 'wynajem')),

    ('Zaginął Pies',
     'Zaginął czarny labrador, wabi się Max. Ostatnio widziany 15 czerwca w okolicy parku miejskiego przy ul. Zielonej. Max jest przyjazny i ma na sobie niebieską obrożę z identyfikatorem. Bardzo prosimy o pomoc w jego odnalezieniu. Dla znalazcy przewidziana nagroda. Kontakt: 987-654-321.',
     true,
     'user',
     '2024-09-04',
     '2024-10-04',
     (SELECT id FROM category WHERE name = 'zagubione')),

    ('Poszukiwany Pracownik do Biura',
     'Firma XYZ poszukuje pracownika do działu administracji. Wymagania: dobra znajomość pakietu MS Office, umiejętność pracy w zespole, komunikatywność. Oferujemy stabilne zatrudnienie, atrakcyjne wynagrodzenie oraz możliwość rozwoju zawodowego. CV prosimy przesyłać na adres: praca@xyz.com do 30 czerwca.',
     true,
     'user',
     '2024-07-07',
     '2024-08-07',
     (SELECT id FROM category WHERE name = 'poszukiwanie pracy')),

    ('Sprzedam Samochód',
     'Sprzedam samochód osobowy marki Toyota, model Corolla, rocznik 2015. Przebieg: 75,000 km. Auto jest w bardzo dobrym stanie technicznym i wizualnym, regularnie serwisowane, garażowane. Wyposażenie: klimatyzacja, system nawigacji, tempomat, czujniki parkowania. Cena: 35,000 zł. Możliwość jazdy próbnej po wcześniejszym umówieniu. Kontakt: 555-666-777.',
     false,
     'user',
     '2024-07-05',
     '2024-08-05',
     (SELECT id FROM category WHERE name = 'sprzedaż'));